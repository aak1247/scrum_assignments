import * as React from "react";
import { List, Typography } from 'antd'

import "./Todo.scss";
import { ITodoProps } from 'src/interfaces';

const useState = React.useState;

const Todo = (props: ITodoProps) => {
  let [count, setCount] = useState(0);

  return (
    <List.Item
      key={props.id} 
    >
      <List.Item.Meta>

      </List.Item.Meta>
      <Typography.Text>

        {count}
        <button onClick={() => setCount(count + 1)}>{count}</button>
        {props.id}
        {props.content}
        {props.createdTime}

      </Typography.Text>
        
    </List.Item>
    
  )
}

export default Todo;
