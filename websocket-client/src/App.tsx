import { useState, type JSXElementConstructor, type Key, type ReactElement, type ReactNode, type ReactPortal } from 'react';
import './App.css';
import { useWebSocket } from './hooks/WebsocketHook';
import ConnectionSection from './components/ConnectionSection';

function App() {
  const [name, setName] = useState('');
  const { connected, connect, disconnect, messages, sendMessage } = useWebSocket();

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
      </div><div className="row">
        <div className="col-md-12">
          <table id="conversation" className="table table-striped">
            <thead>
              <tr>
                <th>Greetings</th>
              </tr>
            </thead>
            <tbody id="greetings">
              {messages.map((msg: string | number | bigint | boolean | ReactElement<unknown, string | JSXElementConstructor<any>> | Iterable<ReactNode> | ReactPortal | Promise<string | number | bigint | boolean | ReactPortal | ReactElement<unknown, string | JSXElementConstructor<any>> | Iterable<ReactNode> | null | undefined> | null | undefined, idx: Key | null | undefined) => (
                <tr key={idx}>
                  <td>{msg}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>

  );
}

export default App;