import * as React from "react";
import { ITodoPageProps } from "../interfaces";

import "./TodoPage.scss";
import { Layout, Typography, Col, Row } from "antd";
import { autobind } from "core-decorators";
import { observer, inject } from "mobx-react";
import TodoList from "src/components/todoList/TodoList";

const { Header, Content, Footer } = Layout;

@inject("common", "todo")
@observer
@autobind
class TodoPage extends React.Component<ITodoPageProps, {}> {
  state = {
    count: 0
  };

  constructor(props: ITodoPageProps) {
    super(props);
    props.todo.addAll([
      {
        id: 0,
        content: "Restful API homework",
        createTime: new Date()
      },
      {
        id: 1,
        content: "React Todos homework",
        createTime: new Date()
      }
    ]);
  }

  public render() {
    const { Title } = Typography;
    return (
      <Layout className="layout todo-page">
        <Header
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            boxShadow: "1px 0 5px 1px rgba(100,100,100,0.9)",
            backgroundColor: "#2196f3"
          }}
        >
          <Title
            level={2}
            style={{
              color: "white"
            }}
          >
            Todo of Today
          </Title>
        </Header>
        <Content style={{ padding: "50px" }}>
          <Row
            // align="top"
            // justify="start"
            gutter={8}
            style={{
              padding: 24,
              minHeight: 280
            }}
          >
            <Col
              xs={2}
              sm={4}
              md={2}
              lg={3}
              xl={3}
              xxl={3}
              className="todo-page_col"
            />
            <Col
              xs={20}
              sm={16}
              md={10}
              lg={8}
              xl={8}
              xxl={8}
              className="todo-page_col"
            >
              <TodoList
                todos={this.props.todo.unfinishedTodos}
                title="Todo"
                className="todo-page_unfinished"
                canAdd={true}
              />
            </Col>
            <Col
              xs={2}
              sm={4}
              md={1}
              lg={1}
              xl={1}
              xxl={1}
              className="todo-page_col"
            />
            <Col
              xs={2}
              sm={4}
              md={1}
              lg={1}
              xl={1}
              xxl={1}
              className="todo-page_col"
            />
            <Col
              xs={20}
              sm={16}
              md={10}
              lg={8}
              xl={8}
              xxl={8}
              className="todo-page_col"
            >
              <TodoList
                todos={this.props.todo.doneTodos}
                title="Done"
                className="todo-page_done"
                canAdd={false}
              />
            </Col>
          </Row>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          Powered by aak1247 ©2019
        </Footer>
      </Layout>
    );
  }
}

export default TodoPage;
