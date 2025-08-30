import csv, json

def main():
    """Convert CSV to JSON format for vocab processing"""
    clean_vocabs = []
    print('Convert csv to json')
    
    # Read CSV and convert to structured data
    with open('jlpt_vocab.csv', 'r', encoding='utf-8') as file:
        csv_reader = csv.reader(file)
        
        header = next(csv_reader)
        print(f"Header: {header}")
        
        # Convert each row to dictionary format
        for row in csv_reader:
            clean_vocab = {
                'expression': row[0],  # Japanese characters (kanji/hiragana/katakana)
                'reading': row[1],     # Furigana/pronunciation
                'meaning': row[2],     # English translation
                'topic': row[3]        # JLPT level (N1-N5)
            }
            clean_vocabs.append(clean_vocab)
    print('convert done')

    # Save as JSON file
    print('making json file')
    with open('clean_vocabs.json', 'w', encoding='utf-8') as output_file:
        json.dump(clean_vocabs, output_file, ensure_ascii=False, indent=2)
    print('process is completed')

if __name__ == "__main__":
    main()