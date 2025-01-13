import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import UploadImage from './components/UploadImage'
import CallAPI from './components/CallAPI'
import Header from './components/Header'
import Translation from './components/Translation'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <Header/>
        <UploadImage/>
        <CallAPI/>
        <Translation originalText='hello'/>
      </div>
    </>
  )
}

export default App
