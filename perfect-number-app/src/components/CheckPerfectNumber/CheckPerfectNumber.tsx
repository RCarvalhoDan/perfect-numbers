import { useState } from "react";
import axios from "axios";

function CheckPerfectNumber() {
  const [number, setNumber] = useState<string>("");
  const [result, setResult] = useState<string | null>(null);

  const handleCheck = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/perfect-number/${number}`
      );
      setResult(
        response.data.message
          ? `${number} is a perfect number`
          : `${number} is not a perfect number`
      );
    } catch (error) {
      console.error(error);
      setResult("Error checking the number");
    }
  };

  return (
    <div>
      <h2>Check if a Number is Perfect</h2>
      <input
        type='text'
        value={number}
        onChange={(e) => setNumber(e.target.value)}
        placeholder='Enter a number'
      />
      <button onClick={handleCheck}>Check</button>
      {result && <p>{result}</p>}
    </div>
  );
}

export default CheckPerfectNumber;
