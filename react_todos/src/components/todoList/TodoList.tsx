import { List, Badge, Typography, Button, Modal } from "antd";
import * as React from "react";

import { ITodoListProps } from "src/interfaces";
import Todo from "../todo/Todo";
import "./TodoList.scss";
import TodoModal from "../todoModal/TodoModal";

const { Title } = Typography;

const TodoList = (props: ITodoListProps) => {
  let [showAdd, setShowAdd] = React.useState(false);
  function showAddModel() {
    setShowAdd(true);
  }
  function save() {}
  function cancelAdd() {
    setShowAdd(false);
  }
  return (
    <List
      className="todo-list"
      header={
        <div className="todo-list_header">
          <Badge count={props.todos.length} overflowCount={10}>
            <Title level={4} className="todo-list_title">
              {props.title}
            </Title>
          </Badge>

          {props.canAdd && (
            <Button type="primary" icon="plus" onClick={showAddModel} />
          )}
        </div>
      }
      dataSource={props.todos}
      renderItem={item => <Todo {...item} />}
      locale={{
        emptyText: "No tasks"
      }}
    >
      <TodoModal visible={showAdd} onSave={save} onCancel={cancelAdd} />
    </List>
  );
};

export default TodoList;
