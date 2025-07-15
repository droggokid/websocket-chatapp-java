import { useEffect, useState } from "react";
import axios from 'axios';

export const useImageApi = () => {
    const [url, setUrl] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let isMounted = true;
        let currentUrl = "";
        setLoading(true);

        axios
            .get("http://localhost:8080/api/image/1", { responseType: "blob" })
            .then(({ data: blob }) => {
                if (!isMounted) return;
                const objectUrl = URL.createObjectURL(blob as Blob);
                currentUrl = objectUrl;
                setUrl(objectUrl);
                console.log("Image loaded successfully");
                if (isMounted) {
                    setLoading(false);
                }
            })
            .catch((err) => {
                if (!isMounted) return;
                console.error("Failed to load image:", err);
                setError("Failed to load image");
                if (isMounted) {
                    setLoading(false);
                }
            });

        return () => {
            isMounted = false;
            if (currentUrl) {
                URL.revokeObjectURL(currentUrl);
            }
        };
    }, []);

    return {
        url,
        loading,
        error
    };
};