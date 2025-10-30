import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './App.css';
import Home from './components/Home/Home';
import Login from './components/Login/Login';
import Signup from './components/Signup/Signup';
import Navbar from './components/Navbar/Navbar';
import Profile from './components/Profile/Profile';
import AddPetForm from './components/admin/AddPetForm/AddPetForm';
import AdminDashboard from './components/admin/AdminDashboard/AdminDashboard';

function App() {
  return (
    <>
     <Router>
      <Navbar/>
      <Routes>

        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/add-pet" element={<AddPetForm/>} />

      </Routes>
    </Router>
    </>
  );
}

export default App;
