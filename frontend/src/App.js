import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UsersView from './pages/View';
import Form from './pages/Form';


function App() {


  return (
    <Router>
      <Routes>
        <Route path='/entity' element={<UsersView />} />
        <Route path='/add' element={<Form />} />
      </Routes>
    </Router>
  );
}

export default App;
