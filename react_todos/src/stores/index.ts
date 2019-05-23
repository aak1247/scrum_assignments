import TodoStore from "./TodoStore";
import CommonStore from './CommonStore';

const rootStore: any = {
  todo: new TodoStore(),
  common: new CommonStore()
}

export default rootStore