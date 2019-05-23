import * as React from "react";
import { List, Typography, Icon } from 'antd'

import "./Todo.scss";
import { ITodoProps } from 'src/interfaces';

const useState = React.useState;

const Todo = (props: ITodoProps) => {
  let [choose, setChoose] = useState(false);

  return (
    <List.Item key={props.id} className="todo">
      <Typography.Text className="todo_left">
        {props.id}
        {props.content}
        {props.createdTime}
      </Typography.Text>

      <Icon
        type="check-circle"
        theme="twoTone"
        twoToneColor="#52c41a"
        onClick={() => setChoose(!choose)}
      />
    </List.Item>
  );
}

export default Todo;
