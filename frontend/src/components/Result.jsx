import React, { Component, Fragment } from 'react';
import { Table } from 'reactstrap';
import ResultRow from './ResultRow';

import '../css/App.css';

class Result extends Component {
    render(){
        var showResults = false;
        if(this.props.results.length > 0){
            showResults = true;
        }

        return (
            <Fragment>
                {showResults &&
                    <Table className="result-table">
                        <thead className="result-table-header">
                            <tr>
                                <th>Stasjon</th>
                                <th>Tilgjengelige låser</th>
                                <th>ledige sykler</th>
                            </tr>
                        </thead>
                        <tbody>
                        {
                            this.props.results.map(
                                (result, i) => <ResultRow row={result} key={i} />
                            )
                        }
                        </tbody>
                    </Table>
                }
            </Fragment>
        );
    }
}

export default Result;