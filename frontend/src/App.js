import 'bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    users: []
  };

  /*async componentDidMount() {
    const response = await fetch("/users");
    const body = await response.json();
    this.setState({users : body});
  }*/

  render () {
    return (
        <h1>H1</h1>
    )

    /*const {users} = this.state;
    return (
        <div className="App">
          <h1>Users</h1>
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Users</h2>
              {users.map(user =>
                    <div key={user.id}>
                      {user.username} ({user.password})
                    </div>
                )}
            </div>
          </header>
        </div>
    )*/
  }
}
export default App;
