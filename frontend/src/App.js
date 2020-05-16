import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import MainPage from './components/MainPage';
import './css/App.css';

class App extends Component {
    render(){
        return (
            <Router basename={'/sykkelstativer/'}>
                <div>
                    <Route exact path="/" component={MainPage} />
                </div>
            </Router>
        );
    }
}

export default App;
