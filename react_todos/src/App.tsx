import * as React from "react";
import { Router, Route, Switch } from "react-router";
import { createBrowserHistory } from "history";
import { Provider } from "mobx-react";
import { RouterStore, syncHistoryWithStore } from "mobx-react-router";

import "./App.css";
import rootStore from "./stores";
import { MobxDevTools } from "./partials";
import TodoPage from "./pages/TodoPage";

const browserHistory = createBrowserHistory();
const routerStore = new RouterStore();
const history = syncHistoryWithStore(browserHistory, routerStore);

class App extends React.Component<any, {}> {
  public render() {
    return (
      <Provider {...rootStore}>
        <MobxDevTools>
          <Router history={history}>
            <Switch>
              <Route path="/" component={TodoPage} />
            </Switch>
          </Router>
        </MobxDevTools>
      </Provider>
    );
  }
}

export default App;
