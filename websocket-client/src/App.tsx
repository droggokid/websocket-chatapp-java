import { useState } from 'react';
import './App.css';
import { useWebSocket } from './hooks/WebsocketHook';
import ConnectionSection from './components/ConnectionSection';
import UserCard from './components/UserCard';
import { useImageApi } from './hooks/imageHook';

function App() {
  const [name, setName] = useState('');
  const { connected, connect, disconnect, sendMessage } = useWebSocket();
  const { url } = useImageApi();

  const handleSendName = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (name.trim()) {
      sendMessage(name);
      setName('');
    }
  };

  return (
    <>
      <div id="main-content" className="container">
        <div className="row">
          <ConnectionSection connect={connect} disconnect={disconnect} connected={connected} />

          <form className="form-inline" onSubmit={handleSendName}>
            <div className="form-group">
              <label htmlFor="name">What is your name?</label>
              <input
                type="text"
                id="name"
                className="form-control"
                placeholder="Your name here..."
                value={name}
                onChange={e => setName(e.target.value)} />
            </div>
            <button id="send" className="btn btn-default" type="submit" disabled={!connected || !name.trim()}>
              Send
            </button>
          </form>
        </div>
      </div>
      <UserCard name="Radu" url={url} message='asd' />
    </>

  );
}

export default App;