import { ITodo, ITodoStore } from './../interfaces/index';
import { observable, action} from 'mobx';

class TodoStore implements ITodoStore{
  @observable todos: (ITodo)[] = [];
  @action async addTodo(data: ITodo) {
    this.todos.push(data);
  }
  @action async removeTodo(id: number) {
    const newTodo = this.todos.filter(todo => todo.id != id)
    this.todos = [...newTodo]
  }
  @action async clear() {
    this.todos = []
  }
  @action async addAll(data: ITodo[]) {
    this.todos = this.todos.concat(...data)
  }
}

export default TodoStore;