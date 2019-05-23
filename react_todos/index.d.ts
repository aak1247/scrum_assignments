declare module 'react-dragula' {
  function dragula(container: any[]): any;
  function dragula(container: any[], options: Object): any;
  export default dragula;
}

declare module 'react-draggable-tags' {
  import * as React from 'react';
  interface IDraggableAreaProps extends React.ComponentProps<any>{
    isList: boolean;
    tags: any[];
    render: Function;
  }
  export class DraggableArea extends React.Component<IDraggableAreaProps, any>{
    render(): JSX.Element;  

  }
}