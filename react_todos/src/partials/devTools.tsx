import * as React from 'react';

export class MobxDevTools extends React.Component<{}, {}> {

  renderDevTool() {
    if (process.env.NODE_ENV !== 'production') {
      const DevTools = require('mobx-react-devtools').default;
      return (<DevTools />);
    }
    return null;
  }

  render() {
    return (
      <div>
      { this.props.children }
        { this.renderDevTool() }
      </div>
    );
  }
}