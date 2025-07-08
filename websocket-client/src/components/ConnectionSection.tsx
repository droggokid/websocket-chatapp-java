import { useBackendApi } from "../hooks/useBackendHook";
import Button from "./Button";

interface ConnectionSectionProps {
    readonly connect: () => void;
    readonly disconnect: () => void;
    readonly connected: boolean;
}

export default function ConnectionSection({ connect, disconnect, connected }: ConnectionSectionProps) {
    const { loading, error, testConnection } = useBackendApi();

    return (
        <div className="col-md-6">
            <div style={{ marginBottom: '20px' }}>
                <div style={{ marginBottom: '10px', fontWeight: 'bold' }}>
                    Status: {connected ? '🟢 Connected' : '🔴 Disconnected'}
                </div>

                <Button
                    label={connected ? "Connected" : "Connect"}
                    onClick={connect}
                    disabled={connected}
                    variant={connected ? "success" : "primary"}
                />
                <Button
                    label="Disconnect"
                    onClick={disconnect}
                    disabled={!connected}
                    variant="danger"
                />
                <Button
                    label={loading ? "Testing..." : "Test Backend"}
                    onClick={testConnection}
                    disabled={loading}
                    variant="secondary"
                />
                {error && <div style={{ color: 'red' }}>{error}</div>}
            </div>
        </div>

    )
}