import { useState } from "react";
import axios from "axios";

function FindPerfectNumbersInRange() {
  const [lowerBoundValue, setLowerBoundValue] = useState<string>("");
  const [upperBoundValue, setUpperBoundValue] = useState<string>("");
  const [result, setResult] = useState<number[] | null>(null);

  const handleFind = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/perfect-number/in-range",
        {
          params: {
            lowerBoundValue: lowerBoundValue,
            upperBoundValue: upperBoundValue,
          },
        }
      );
      setResult(response.data.message);
    } catch (error) {
      console.error(error);
      setResult(null);
    }
  };

  return (
    <div>
      <h2>Find Perfect Numbers in Range</h2>
      <input
        type='text'
        value={lowerBoundValue}
        onChange={(e) => setLowerBoundValue(e.target.value)}
        placeholder='Start'
      />
      <input
        type='text'
        value={upperBoundValue}
        onChange={(e) => setUpperBoundValue(e.target.value)}
        placeholder='End'
      />
      <button onClick={handleFind}>Find</button>
      {result && (
        <div>
          <h3>Perfect Numbers:</h3>
          <ul>
            {result.length > 0 ? (
              result.map((num) => <li key={num}>{num}</li>)
            ) : (
              <p>No perfect numbers in this range</p>
            )}
          </ul>
        </div>
      )}
    </div>
  );
}

export default FindPerfectNumbersInRange;
