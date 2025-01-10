import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CallAPI: React.FC = () => {
  const [response, setResponse] = useState<string>('');

  // useEffect to call the API when the component is mounted
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get('/api/call');
        setResponse(res.data); // Display API result
      } catch (error: any) {
        setResponse('Error: ' + error.message); // Handle errors
      }
    };

    fetchData();
  }, []); // Empty dependency array means this will run once on component mount

  return (
    <div>
      {response}
    </div>
  );
};

export default CallAPI;
