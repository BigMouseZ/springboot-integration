export default {
  async submitForm () {
    // 清空错误信息
    this.showNameError = false
    this.showPwdError = false
    // 验证是否为空
    if (this.formData.loginName === '') {
      this.errorInfo.loginName = '请输入用户名'
      this.showNameError = true
    } else if (this.formData.loginPassword === '') {
      this.errorInfo.loginPassword = '请输入密码'
      this.showPwdError = true
    } else {
      let res = await this.request('login', this.formData)
      if (res.data.code === '0') {
        let data = res.data.data
        localStorage.sessionId = data.sessionId // 保存sessionid
        localStorage.userLoginName = _.get(res, 'data.data.userLoginName') // 保存用户名，头像
        localStorage.companyName = _.get(res, 'data.data.resourcesNames')
        localStorage.roleNo = data.roleNo // 保存用户权限组ID
        // this.isremember() // 设置是否记住用户名
        await this.methods('other')
        window.util.websocket.open(window.CONFIG.WEB_SOCKET)
        // 页面跳转
        this.$router.push({
          path: '/'
        })
      } else {
        this.$message.error(res.data.message)
      }
    }
  },
  rememberUserName () {
    if (window.localStorage) {
      let storage = window.localStorage
      // 勾选且用户名不为空，设置storage
      if (this.rememberme && this.formData.loginName !== '') {
        storage.userName = this.formData.loginName
      } else if (storage.loginName) { // 不勾选，且storage中存在loginName，删除
        storage.removeItem('userName')
      }
    }
  },
  getRememberName () {
    // 读取记住的用户名
    if (window.localStorage) {
      let storage = window.localStorage
      this.formData.loginName = storage.userName ? storage.userName : ''
    }
  },
  hiddenErrorInfo (event) {
    let inputId = event.target.id
    if (inputId === 'loginName') {
      this.showNameError = false
    } else {
      this.showPwdError = false
    }
    event.target.className += ' focus'
  },
  removeClassName (event) {
    event.target.className = ''
  },
  async other () {
    // 清除等待提示
    $('#wellcome').remove()
  }
}
