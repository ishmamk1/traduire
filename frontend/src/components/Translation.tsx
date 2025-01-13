import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Props {
  originalText: string;
}

const Translation: React.FC<Props> = ({ originalText }) => {
  const [translations, setTranslations] = useState<Map<string, string>>(new Map());

  const handleCopy = (text: string) => {
    navigator.clipboard.writeText(text).then(
      () => {
        alert(`Copied: "${text}"`);
      },
      (err) => {
        console.error('Could not copy text:', err);
      }
    );
  };

  // Fetch translations
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.post('http://127.0.0.1:5000/translate', { originalText });

        // Convert response to a Map
        const data: Record<string, string> = res.data;
        const mapData = new Map<string, string>(Object.entries(data));

        setTranslations(mapData);
      } catch (error: any) {
        console.error('Error fetching translations:', error);
        setTranslations(new Map([['error', 'Failed to fetch translations.']]));
      }
    };

    fetchData();
  }, [originalText]);

  return (
    <div className="p-6 bg-gray-100 rounded-md shadow-md max-w-xl mx-auto">
      <h2 className="text-2xl font-semibold text-gray-800 text-center mb-4">Translations</h2>
      {Array.from(translations.entries()).map(([key, value]) => (
        <div
          key={key}
          className="flex justify-between items-center bg-white p-4 rounded-lg shadow-sm mb-3"
        >
          <p className="text-gray-700">
            <strong className="text-gray-900">{key}:</strong> {value}
          </p>
          <button
            className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition duration-200"
            onClick={() => handleCopy(value)}
          >
            Copy
          </button>
        </div>
      ))}
    </div>
  );
};

export default Translation;
