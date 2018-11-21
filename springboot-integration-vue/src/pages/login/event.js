export let life = {
  created () {
    this.methods('getRememberName')
    window.util.websocket.close(window.CONFIG.WEB_SOCKET)
  }
}
export let event = {
  clickSubmit () {
    this.methods('submitForm')
  },
  changeRemember () {
    this.methods('rememberUserName')
  },
  focusUserName (event) {
    this.methods('hiddenErrorInfo', event)
  },
  focusPassword (event) {
    this.methods('hiddenErrorInfo', event)
  },
  blurUserName (event) {
    this.methods('removeClassName', event)
  },
  blurPassword (event) {
    this.methods('removeClassName', event)
  }
}
export let watch = {}
