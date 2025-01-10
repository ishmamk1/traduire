import React, { useState, ChangeEvent } from 'react';
import axios from 'axios';

const UploadImage: React.FC = () => {
  const [file, setFile] = useState<File | null>(null);
  const [response, setResponse] = useState<string>('');

  const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setFile(event.target.files[0]);
    }
  };

  const handleUpload = async () => {
    if (!file) {
      setResponse('No file selected');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      const res = await axios.post('http://localhost:8080/api/ocr', formData, {
        headers: {
          'Content-Type': 'multipart/form-data', // Make sure to set the correct content type for file uploads
        },
      });
      setResponse(res.data); // Display response from backend (confirmation message)
    } catch (error: any) {
      setResponse('Error: ' + error.message); // Handle errors
    }
  };

  return (
    <div>
        { !response ? (
            <div>
                <h2>Upload an Image for OCR</h2>
                <input type="file" onChange={handleFileChange} />
                <button onClick={handleUpload}>Upload</button> 
            </div>) : (
        <p>Response: {response}</p>
        
      )}
    </div>
  );
};

export default UploadImage;

