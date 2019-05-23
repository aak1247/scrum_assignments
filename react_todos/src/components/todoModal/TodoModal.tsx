import * as React from "react";
import { Modal, Form, Input, Icon, notification, Button } from "antd";
import { inject, observer } from "mobx-react";

import {
  ITodoModalPropsWithStore,
  ITodoModalProps,
  ITodo
} from "src/interfaces";

const TodoModalWithStore = (props: ITodoModalPropsWithStore) => {
  let [content, setContent] = React.useState("");
  const save = async (content: string) => {
    let todo: ITodo = {
      id: Math.floor(Math.random() * 10000),
      content,
      createdTime: new Date(),
      done: false
    };
    setContent("");
    await props.todo.addNewTodo(todo);
    openNotificationWithIcon();
  };

  const onConfirm = (e: React.MouseEvent) => {
    save(content);
    props.onSave(e);
    props.onCancel(e);
  };

  const openNotificationWithIcon = () => {
    notification["success"]({
      message: "Add success!",
      description: "Your new task has been added to list."
    });
  };
  return (
    <Modal
      title="Add a task"
      visible={props.visible}
      onOk={props.onSave}
      onCancel={props.onCancel}
      footer={[
        <Button key="back" onClick={props.onCancel}>
          Cancel
        </Button>,
        <Button key="submit" type="primary" onClick={onConfirm}>
          Add
        </Button>
      ]}
    >
      <Form onSubmit={props.onSave} className="login-form">
        <Form.Item>
          <Input
            prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
            placeholder="Task content"
            value={content}
            onChange={e => {
              setContent(e.target.value);
            }}
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};

const TodoModal: React.FC<ITodoModalProps> = inject("todo")(TodoModalWithStore);

export default TodoModal;
