import React, { Component } from 'react';

class ResultRow extends Component {
    render() {
        return (
            <tr>
                <td>{this.props.row.station_name}</td>
                <td>{this.props.row.num_docks_available}</td>
                <td>{this.props.row.num_bikes_available}</td>
            </tr>
        );
    }
}

export default ResultRow;