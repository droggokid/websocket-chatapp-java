import Avatar from "@mui/material/Avatar";

interface UserCardProps {
    readonly name: string;
    readonly url: string;
    readonly message: string;
}

export default function UserCard({ name, url, message }: UserCardProps) {
    return (
        <div style={{
            display: "flex",
            alignItems: "center",
            gap: "12px",
            padding: "12px",
            border: "1px solid #dee2e6",
            borderRadius: "8px",
            backgroundColor: "#fff",
            boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
            marginTop: "20px"
        }}>
            <Avatar
                alt={name}
                src={url}
                sx={{ width: 48, height: 48 }}
            />
            <div style={{
                display: "flex",
                flexDirection: "column",
                gap: "4px"
            }}>
                <p style={{
                    margin: 0,
                    fontSize: "16px",
                    fontWeight: "600",
                    color: "#333"
                }}>
                    {name}
                </p>
                <p style={{
                    margin: 0,
                    fontSize: "14px",
                    color: "#6c757d"
                }}>
                    {message}
                </p>
            </div>
        </div>
    );
}