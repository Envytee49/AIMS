import React, { useEffect, useState } from 'react';
import { Container, Row } from 'react-bootstrap';
import './Order.css';
import api from './axiosConfig';
import Loader from "./Loader";
import { useParams } from "react-router-dom";
import ToastUtil from "./utils.jsx";
const Order = () => {
    const [res, setRes] = useState(null);
    const [orderState, setOrderState] = useState("");
    const [loading, setLoading] = useState(true);
    const { id } = useParams();

    const fetchOrders = async () => {
        try {
            const response = await api.get(`/order/${id}`);
            setRes(response.data);
            setOrderState(response.data.order.orderState);
        } catch (err) {
            console.error('Error fetching:', err);
        } finally {
            setLoading(false);
        }
    };

    async function cancelOrder() {
        try {
            await api.delete(`/order/${res.order.id}`);
            ToastUtil.showToastSuccess("Cancel Order Successfully");
            // Optionally, update the order state or provide user feedback
        } catch (err) {
            ToastUtil.showToastError("Failed to cancel order");
            console.error('Error canceling order:', err);
        }
    }

    useEffect(() => {
        fetchOrders();
    }, [id]);

    if (loading) {
        return <Loader />;
    }

    if (!res) {
        return <div>Order doesn't exist</div>;
    }

    return (
        <>
            {orderState !== "PENDING" ? (
                <div>Order is {orderState}</div>
            ) : (
                <Container>
                    <Row>Transaction Details:</Row>
                    <Row>Transaction ID: {res.paymentTransaction.transactionId}</Row>
                    <Row>Transaction Number: {res.paymentTransaction.transactionNum}</Row>
                    <Row>Transaction Content: {res.paymentTransaction.transactionContent}</Row>
                    <Row>Created At: {res.paymentTransaction.createdAt}</Row>
                    <Row>Message: {res.paymentTransaction.message}</Row>
                    <Row>Invoice Details:</Row>
                    <Row>Subtotal:</Row>
                    <Row>Subtotal with VAT:</Row>
                    <Row>Delivery Fee: {res.order.shippingFee}</Row>
                    <Row>Total Amount: {res.amount}</Row>
                    <Row>Delivery Information:</Row>
                    <Row>Name: {res.order.deliveryInfo.name}</Row>
                    <Row>Phone: {res.order.deliveryInfo.phone}</Row>
                    <Row>Email: {res.order.deliveryInfo.email}</Row>
                    <Row>Province: {res.order.deliveryInfo.province}</Row>
                    <Row>Address: {res.order.deliveryInfo.address}</Row>
                    <Row>
                        <div>
                            <button onClick={cancelOrder}>Cancel</button>
                        </div>
                    </Row>
                </Container>
            )}
        </>
    );
};

export default Order;
