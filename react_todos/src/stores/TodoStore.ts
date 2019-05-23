import { ITodo, ITodoStore } from './../interfaces/index';
import { observable, action } from 'mobx';

class TodoStore implements ITodoStore {
  @observable unfinishedTodos: (ITodo)[] = [];
  @observable doneTodos: (ITodo)[] = [];

  @action public async addNewTodo(data: ITodo) {
    this.unfinishedTodos = this.unfinishedTodos.concat([data])
  }

  @action public async removeDoneTodo(id: number) {
    const newTodo = this.doneTodos.filter(todo => todo.id != id)
    this.doneTodos = [...newTodo]
  }

  @action public async clearDone() {
    this.doneTodos = []
  }

  @action public async addAll(data: ITodo[]) {
    this.unfinishedTodos = this.unfinishedTodos.concat(...data)
  }

  @action public async moveToDone(id: number) {
    const doneOne = this.unfinishedTodos.filter(todo => todo.id === id)[0]
    this.unfinishedTodos = this.unfinishedTodos.filter(todo => todo.id !== id)
    if (doneOne) this.doneTodos = this.doneTodos.concat([doneOne])
  }

  @action public async delNewTodo(id: number) {
    this.unfinishedTodos = this.unfinishedTodos.filter(todo => todo.id !== id)
  }

  public async doneAll(data: ITodo[]): Promise<void>;
  public async doneAll(data: number[]): Promise<void>;

  @action public async doneAll(data: any[]): Promise<void> {
    if (!data.length) {
      return;
    }
    if (typeof (data[0]) == 'number') {
      const todos = this.unfinishedTodos.filter(todo => data.includes(todo.id));
      this.unfinishedTodos = this.unfinishedTodos.filter(todo => !data.includes(todo.id))
      this.doneTodos = this.doneTodos.concat(todos)
    } else {
      const todos = this.unfinishedTodos.filter(todo => data.includes(todo))
      this.unfinishedTodos = this.unfinishedTodos.filter(todo => !data.includes(todo))
      this.doneTodos = this.doneTodos.concat(todos)
    }
  }

}


export default TodoStore;