BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "themes" (
	"theme_id"	INTEGER NOT NULL,
	"theme_name"	TEXT NOT NULL,
	PRIMARY KEY("theme_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "topics" (
	"topic_id"	INTEGER NOT NULL,
	"topic_name"	TEXT NOT NULL,
	"theme_id"	INTEGER NOT NULL,
	PRIMARY KEY("topic_id" AUTOINCREMENT),
	FOREIGN KEY("theme_id") REFERENCES "themes"("theme_id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "vocabularies" (
	"vocab_id"	INTEGER NOT NULL,
	"expression"	TEXT NOT NULL,
	"meaning"	TEXT NOT NULL,
	"language_code"	TEXT NOT NULL,
	"reading"	TEXT NOT NULL,
	PRIMARY KEY("vocab_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "topic_vocabulary_relation" (
	"topic_id"	INTEGER NOT NULL,
	"vocab_id"	INTEGER NOT NULL,
	FOREIGN KEY("vocab_id") REFERENCES "vocabularies"("vocab_id") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("topic_id") REFERENCES "topics"("topic_id") ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY("topic_id","vocab_id")
);
COMMIT;
