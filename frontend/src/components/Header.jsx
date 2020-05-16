import React from 'react';
import {Row, Col, Navbar, NavbarBrand } from 'reactstrap';

const Header = () => (
    <header>
        <Navbar fixed="top" light expand="xs" className="border-bottom" style={{ height: 80, backgroundColor: '#444f55'}}>
            <div className="header-left">
                <Row noGutters className="position-relative w-100 align-items-center">
                    <Col className="d-none d-lg-flex justify-content-start">
                        <NavbarBrand className="d-inline-block p-0">
                            <h1>Oslo Bysykkel</h1>
                        </NavbarBrand>
                    </Col>
                </Row>
            </div>
        </Navbar>
    </header>
);

export default Header;