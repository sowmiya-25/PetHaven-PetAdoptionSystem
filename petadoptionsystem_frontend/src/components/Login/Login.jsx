import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { loginUser } from "../../services/userServices";

import './Login.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    loginUser({ email, password })
      .then((res) => {
        alert("Login Successful");
        localStorage.setItem("email", res.data.email);
        localStorage.setItem("role", res.data.role);   // 👈 store role too

        // 👇 Role-based redirect
        if (res.data.role === "ADMIN") {
          navigate("/admin-dashboard");
        } else {
          navigate("/");
        }
      })
      .catch((err) => {
        alert("Invalid email or password");
        console.log(err);
      });
  };

  return (
    <div className='login-page'>
      <div className="login-box">
        <h2>Login</h2>
        <form onSubmit={handleLogin} className="login-form">
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
