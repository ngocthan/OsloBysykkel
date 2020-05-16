import React, { Component, Fragment } from 'react';
import { Row, Col } from 'reactstrap';
import axios from 'axios';
import LoadingSpinner from './LoadingSpinner';
import Result from './Result';
import Header from './Header';

class MainPage extends Component {
    intervalID;
    constructor(props) {
        super(props);
        this.state = {
            fetchInProgress: false,
            stations: []
        };
    }

    componentWillMount() {
        this.getStations();
        this.intervalID = setInterval(this.getStations.bind(this), 10000); //Refresh data every 10 seconds
    }

    componentWillUnmount() {
        clearInterval(this.intervalID);
    }

    getStations(){
        this.setState({fetchInProgress: true});
        var url = '/sykkelstativer/api/getStations';
        axios.get(url)
            .then(response => {
                var tempStations = response.data;
                //Sort stations by name
                tempStations.sort(function(a, b){
                      const A = a.station_name.toUpperCase();
                      const B = b.station_name.toUpperCase();
                      let comparison = 0;
                      if (A > B) {
                        comparison = 1;
                      } else if (A < B) {
                        comparison = -1;
                      }
                      return comparison;
                    }
                );

                this.setState({
                    fetchInProgress: false,
                    stations: tempStations
                });
            })
            .catch(error => {
                console.log(error);
            })
    }

    render() {
        return (
            <Fragment>
                <Header />
                {this.state.fetchInProgress &&
                    <LoadingSpinner />
                }
                <main className="my-5 py-5 mx-2 px-2">
                    <div>
                        <Row noGutters className="pt-2 pt-md-2 w-100 px-4 px-xl-0 position-relative">
                            <Col xs={{ order: 2 }} md={{ size: 10, order: 2 }} tag="section" className="py-5 mb-5 py-md-0 mb-md-0">
                                   <p>Nedenfor vises en liste over de ulike stasjonene, og hvor mange tilgjengelige låser og ledige sykler som er på dem i øyeblikket.</p>
                                   <Result results={this.state.stations} />
                            </Col>
                        </Row>
                    </div>
                </main>
            </Fragment>
        );
    }
}

export default MainPage;