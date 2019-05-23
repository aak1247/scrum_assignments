import * as React from "react";
import { ITodoPageProps } from "../interfaces";

import "./TodoPage.scss";
import Todo from 'src/components/todo/Todo';
import { Layout, Menu, Breadcrumb } from "antd";
import { autobind } from "core-decorators";
import { observer, inject } from "mobx-react";

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
          {
            this.props.todo.todos.map(todo => <Todo {...todo}/>)
          }
          <Breadcrumb style={{ margin: "16px 0" }}>
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
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
