import React from 'react'
import BeatLoader  from "react-spinners/BeatLoader";

const Loader = () => {
  return (
    <div style={{width:'100%',height: '100%', display: 'flex', justifyContent: 'center', alignItems:'center'}}>
        <BeatLoader color="#3c7596" />
    </div>
  )
}

export default Loader