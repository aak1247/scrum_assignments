import * as React from "react";
import { inject, observer } from "mobx-react";
import { List, Typography, Icon } from "antd";
import * as moment from "moment";

import "./Todo.scss";
import { ITodoPropsWithStore, ITodoProps } from "../../interfaces";

const TodoWithStore = (props: ITodoPropsWithStore) => {
  return (
    <List.Item key={props.id} className="todo">
      <Typography.Text className="todo_left">{props.content}</Typography.Text>
      <Typography.Text className="todo_middle">
        {formatTime(props.createdTime)}
      </Typography.Text>
      <div className="todo_icons">
        {props.done || (
          <Icon
            type="minus-circle"
            theme="twoTone"
            onClick={() => props.todo.delNewTodo(props.id)}
          />
        )}
        {props.done && (
          <Icon
            type="delete"
            theme="twoTone"
            twoToneColor="#52c41a"
            onClick={() => props.todo.removeDoneTodo(props.id)}
          />
        )}

        {props.done || (
          <Icon type="check" onClick={() => props.todo.moveToDone(props.id)} />
        )}
      </div>
    </List.Item>
  );
};

const formatTime = (time: Date): string => {
  return moment(time).fromNow();
};

const Todo: React.FC<ITodoProps> = inject("todo")(observer(TodoWithStore));
export default Todo;
