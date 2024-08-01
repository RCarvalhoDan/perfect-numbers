import { CheckPerfectNumber, FindPerfectNumbersInRange } from "./components";

function App() {
  return (
    <div className='App'>
      <header className='App-header'>
        <h1>Perfect Number Checker</h1>
      </header>
      <main>
        <CheckPerfectNumber />
        <FindPerfectNumbersInRange />
      </main>
    </div>
  );
}

export default App;
