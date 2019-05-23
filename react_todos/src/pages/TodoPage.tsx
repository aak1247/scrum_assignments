import * as React from "react";
import { ITodoPageProps } from "../interfaces";

import "./TodoPage.scss";
import { Layout, Menu, Breadcrumb } from "antd";
import { autobind } from "core-decorators";
import { observer, inject } from "mobx-react";
import TodoList from 'src/components/todoList/TodoList';

const { Header, Content, Footer } = Layout;

@inject("common", "todo")
@observer
@autobind
class TodoPage extends React.Component<ITodoPageProps, {}> {
  state = {
    count: 0
  }

  constructor(props: ITodoPageProps) {
    super(props)
    props.todo.addAll([
      {
        id: 0,
        content: "Restful API homework",
        createTime: new Date()
      }
    ]);
  }

  public render() {
    return (
      <Layout className="layout">
        <Header>
          <Menu mode="horizontal" style={{ lineHeight: "64px" }} />
        </Header>
        <Content style={{ padding: "0 50px" }}>
          <TodoList
            todos={this.props.todo.unfinishedTodos}
            title="未完成"
          />
          <TodoList
            todos={this.props.todo.doneTodos}
            title="已完成"
          />
          <Breadcrumb style={{ margin: "16px 0" }}>
            <Breadcrumb.Item>Home
            
            </Breadcrumb.Item>
            <Breadcrumb.Item>
            
            </Breadcrumb.Item>
            <Breadcrumb.Item>

            </Breadcrumb.Item>
          </Breadcrumb>
          <div style={{ background: "#fff", padding: 24, minHeight: 280 }}>
            Content
          </div>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          Ant Design ©2018 Created by Ant UED
        </Footer>
      </Layout>
    );
  }
}

export default TodoPage;
