import * as React from "react";
import "./Todo.scss";
import { ITodoProps } from 'src/interfaces';

const useState = React.useState;

const Todo = (props: ITodoProps) => {
  let [count, setCount] = useState(0);

  return (<div className="todo">
    {count}
    <button onClick={() => setCount(count + 1)}>{count}</button>
    {props.id}
    {props.content}
    {props.createdTime}
    </div>)
}

export default Todo;
