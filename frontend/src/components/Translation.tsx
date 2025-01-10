import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Translator: React.FC = () => {
  const [translations, setTranslations] = useState<Array<string>>([]);

  

  // useEffect to call the API when the component is mounted
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get('/api/call');
        setTranslations([res.data]); // Display API result
      } catch (error: any) {
        setTranslations(['Error: ' + error.message]); // Handle errors
      }
    };

    fetchData();
  }, []); // Empty dependency array means this will run once on component mount

  return (
    <div>
      {translations}
    </div>
  );
};

export default Translator;