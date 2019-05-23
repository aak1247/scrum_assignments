import { observable, action } from 'mobx';


class CommonStore {
  @observable locale:string = "zh"
  @action async changeLanguage(newLocale: string) {
    this.locale = newLocale
  }
}

export default CommonStore;