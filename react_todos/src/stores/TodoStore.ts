import { ITodo, ITodoStore } from './../interfaces/index';
import { observable, action, computed } from 'mobx';

class TodoStore implements ITodoStore {
  @observable todos: (ITodo)[] = observable.array([]);


  @computed get unfinishedTodos(): (ITodo)[] {
    return this.todos.filter(todo => !todo.done)
  }

  @computed get doneTodos(): (ITodo)[] {
    return this.todos.filter(todo => todo.done)
  }

  @action public async addNewTodo(data: ITodo) {
    data = observable(data)
    this.todos = this.todos.concat([data])
  }

  @action public async removeDoneTodo(id: number) {
    const newTodo = this.doneTodos.filter(todo => todo.id != id)
    this.todos = observable.array([...newTodo, ...this.unfinishedTodos])
  }

  @action public async clearDone() {
    this.todos = observable.array(this.unfinishedTodos)
  }

  @action public async addAll(data: ITodo[]) {
    data = data.map(d => observable(d))
    this.todos = this.todos.concat(...data)
  }

  @action public async moveToDone(id: number) {
    const doneOne = this.unfinishedTodos.filter(todo => todo.id === id)[0]
    if (doneOne) {
      doneOne.done = true
    }
  }

  @action public async delNewTodo(id: number) {
    this.todos = this.todos.filter(todo => todo.id !== id)
  }

  public async doneAll(data: ITodo[]): Promise<void>;
  public async doneAll(data: number[]): Promise<void>;

  @action public async doneAll(data: any[]): Promise<void> {
    if (!data.length) {
      return;
    }
    if (typeof (data[0]) == 'number') {
      this.unfinishedTodos.filter(todo => data.includes(todo.id)).forEach(todo => todo.done = true);
    } else {
      const ids = data.map(d => d.id);
      this.unfinishedTodos.filter(todo => ids.includes(todo.id)).forEach(todo => todo.done = true)
    }
  }

}


export default TodoStore;