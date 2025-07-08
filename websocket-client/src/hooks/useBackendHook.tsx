import { useState } from 'react';
import axios from 'axios';

export const useBackendApi = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const testConnection = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await axios.get("http://localhost:8080/users");
            console.log("Backend response: ", response.data);
            return response.data;
        } catch (error) {
            console.error("Backend connection failed:", error);
            setError("Backend connection failed");
            throw error;
        } finally {
            setLoading(false);
        }
    };

    return {
        loading,
        error,
        testConnection
    };
};