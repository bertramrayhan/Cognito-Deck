# Cognito Deck 🃏

Cognito Deck is an open-source, Android-based smart flashcard application designed for language learners. It aims to bridge the gap between knowing a word and using it in context.

This project is currently in its early development stages.

## ✨ The Core Problem & Our Solution

Many language learners face a frustrating problem: they can memorize hundreds of flashcards, but struggle to actually *use* the words in real sentences. You can recognize a word, but you can't apply it. This is the gap between **passive recognition** and **active use**.

Cognito Deck is designed to solve this exact problem.

Our core philosophy is to force active recall and contextual application. We do this through two key pillars:

1.  **You Control What You Learn:** Start immediately by importing vocabulary from our pre-populated library (e.g., JLPT levels), or create your own custom topics. Your learning path is 100% relevant to you.

2.  **The Automatic Quiz Engine (The Killer Feature):** This is what makes Cognito Deck different. Instead of just showing you the same card over and over, the app will take the vocabulary you are currently studying and **automatically generate a variety of contextual exercises**.
    *   Fill-in-the-blanks
    *   Multiple Choice questions
    *   Sentence Ordering puzzles
    *   And many more

    By completing these exercises, you are forced to actively use the words in different situations, which dramatically improves retention and your ability to use the language in the real world. This turns passive memorization into active, applied learning.

## 🛠️ Tech Stack

- **Platform:** Android (Java)
- **Architecture:** Single Activity, Multiple Fragments (using Android Navigation Component)
- **Database:** Room with a pre-populated SQLite database
- **Processing Data:** Python

## 🤝 How to Contribute

We are actively looking for contributors! Whether you're a developer, a designer, or just have great ideas, we'd love for you to join.

Please check out our [**CONTRIBUTING.md**](CONTRIBUTING.md) file for guidelines and see the [**Issues**](https://github.com/bertramrayhan/Cognito-Deck/issues ) tab for tasks you can help with.


## Data Source & Licensing

This project utilizes Japanese vocabulary data sourced from the "JLPT Words by Level" dataset on Kaggle, created by Robin Pourtaud.

- **Dataset:** [JLPT Words by Level](https://www.kaggle.com/datasets/robinpourtaud/jlpt-words-by-level )
- **Author:** Robin Pourtaud
- **License:** [Creative Commons Attribution 4.0 International (CC BY 4.0)](https://creativecommons.org/licenses/by/4.0/ )

In compliance with the CC BY 4.0 license, this project and its source code are made available. We are grateful to the original author for providing this valuable data.

*This project is a personal learning journey and is being built in public. Let's build something cool together!*
