import json

# Configuration
THEME_NAME = 'JLPT'

def convert_json_to_sql():
    """Convert JSON vocabulary data to SQL INSERT statements with proper relationships"""
    
    # Load vocabulary data from JSON
    with open('clean_vocabs.json', 'r', encoding='utf-8') as file:
        vocabs = json.load(file)
    
    # Extract unique topics (JLPT levels)
    topics = set()
    for vocab in vocabs:
        topic = vocab.get('topic', '')
        if topic:
            topics.add(topic)
    
    # Generate SQL file
    with open('vocabularies.sql', 'w', encoding='utf-8') as sql_file:
        sql_file.write("-- SQL INSERT statements for vocabularies with relationships\n")
        sql_file.write("-- Generated from clean_vocabs.json\n\n")
        
        # Insert theme record (parent category for topics)
        sql_file.write(f"INSERT OR IGNORE INTO themes (theme_name) VALUES ('{THEME_NAME}');\n\n")
        
        # Insert topics (JLPT levels: N1, N2, N3, N4, N5)
        sql_file.write("-- Insert topics (JLPT levels)\n")
        for topic in sorted(topics):
            topic_escaped = topic.replace("'", "''")  # Escape single quotes for SQL safety
            # Use subquery to get theme_id dynamically
            sql_file.write(f"""INSERT OR IGNORE INTO topics (topic_name, theme_id) 
            VALUES ('{topic_escaped}', (SELECT theme_id FROM themes WHERE theme_name = '{THEME_NAME}'));
            """)
        
        # Bulk insert vocabularies for better performance
        sql_file.write("\n-- Insert vocabularies (bulk insert for performance)\n")
        sql_file.write("INSERT INTO vocabularies (expression, meaning, language_code, reading) VALUES\n")
        
        # Prepare all vocabulary values for bulk insert
        vocab_values = []
        for vocab in vocabs:
            # Escape single quotes to prevent SQL injection
            expression = vocab.get('expression', '').replace("'", "''")
            meaning = vocab.get('meaning', '').replace("'", "''")
            reading = vocab.get('reading', '').replace("'", "''")
            vocab_values.append(f"('{expression}', '{meaning}', 'jpn', '{reading}')")
        
        # Write all values at once, comma-separated
        sql_file.write(',\n'.join(vocab_values))
        sql_file.write(';\n')
        
        # Create topic-vocabulary relationships (optimized batch inserts)
        sql_file.write("\n-- Create relationships (optimized with batch inserts)\n")
        
        # Group expressions by topic for batch processing
        relationships_by_topic = {}
        for vocab in vocabs:
            topic = vocab.get('topic', '').replace("'", "''")
            expression = vocab.get('expression', '').replace("'", "''")
            
            if topic and expression:
                if topic not in relationships_by_topic:
                    relationships_by_topic[topic] = []
                relationships_by_topic[topic].append(expression)
        
        # Generate relationship INSERT statements per topic
        # This reduces from thousands of individual INSERTs to just a few batch INSERTs
        for topic, expressions in relationships_by_topic.items():
            if expressions:
                sql_file.write(f"""INSERT INTO topic_vocabulary_relation (topic_id, vocab_id) 
            SELECT t.topic_id, v.vocab_id 
            FROM topics t, vocabularies v 
            WHERE t.topic_name = '{topic}' 
            AND v.language_code = 'jpn'
            AND v.expression IN ({', '.join([f"'{expr}'" for expr in expressions])});
            """)
    
    # Print completion summary
    print(f"\n'vocabularies.sql' has been created")
    print(f"Total vocabulary: {len(vocabs)}")
    print(f"Unique topics: {len(topics)} ({', '.join(sorted(topics))})")

def main():
    """Main function to execute JSON to SQL conversion"""
    print("=== JSON to SQL Converter ===")

    try:
        convert_json_to_sql()
    except FileNotFoundError:
        print("Error: File 'clean_vocabs.json' not found!")
        print("Please ensure the JSON file exists in the current directory.")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    main()