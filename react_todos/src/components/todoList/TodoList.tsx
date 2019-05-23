import { List, Badge } from "antd";

import { ITodoListProps } from "src/interfaces";
import * as React from "react";
import Todo from "../todo/Todo";
import './TodoList.scss'

const TodoList = (props: ITodoListProps) => {
  return (
    <List
      header={
        <Badge count={props.todos.length} overflowCount={10}>
          <div className="todo-list_title">{props.title}</div>
        </Badge>
      }
      dataSource={props.todos}
      renderItem={item => <Todo {...item} />}
    />
  );
};

export default TodoList;
