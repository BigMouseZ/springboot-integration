import md5 from '../../libs/md5.js'
export default {
  async login (formData) {
    let result
    await this.api('client.user.login', {
      'userLoginName': formData.loginName,
      'userPassword': md5(formData.loginPassword)
    }, {
      loading: {
        target: $('body')[0]
      }
    }).then(res => {
      result = res
    })
    return result
  }
}
