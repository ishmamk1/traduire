import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import UploadImage from './components/UploadImage'
import CallAPI from './components/CallAPI'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <UploadImage/>
        <CallAPI/>
      </div>
    </>
  )
}

export default App
