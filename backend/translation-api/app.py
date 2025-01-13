import collections
from urllib import request, response
import requests
from flask import Flask, request, jsonify
from googletrans import Translator
from flask_cors import CORS


app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "http://localhost:5173"}})

languages = {
    'en': 'English',
    'es': 'Spanish',
    'fr': 'French',
    'de': 'German',
    'zh-cn': 'Chinese (Simplified)',
    'ja': 'Japanese',
    'ru': 'Russian',
    'ar': 'Arabic',
}

@app.route('/translate', methods=["POST", 'GET'])
def translate():

    
    data = request.get_json()
    print(data)
    if not data or 'originalText' not in data:
        return jsonify({"error": "No text provided"}), 400

    original_text = data['originalText']
    print(original_text)


    translator = Translator()

    detected_language = translator.detect( original_text ).lang

    translations = collections.defaultdict( str )
    for language in languages.keys():
        print(language )
        translations[language] = translator.translate( original_text, src=detected_language, dest=language ).text

    for key, value in translations.items():
        print( key, value )

    return translations
    

if __name__ == '__main__':
    app.run(debug=True)