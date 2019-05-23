import {List} from 'antd'

import { ITodoListProps } from "src/interfaces";
import * as React from 'react';
import Todo from '../todo/Todo';

const TodoList = (props: ITodoListProps) => {
  return (
    <List
      header={
        <div>
          {props.title}
        </div>
      }
      dataSource={props.todos}
      renderItem={item => (
        <Todo {...item}></Todo>
      )}
    />
  );
}

export default TodoList;