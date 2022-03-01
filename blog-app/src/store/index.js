import Vuex from 'vuex'
import Vue from 'vue'
import {getToken, setToken, removeToken} from '@/request/token'
import {login, getUserInfo, logout, register} from '@/api/login'
import user from '@/api/user';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: '',
    ip: '',
    cityName: '',
    nickName:'',
    email:'',
    token: getToken(),
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_IP: (state, ip) => {
      state.ip = ip
    },
    SET_CITY_NAME: (state, cityName) => {
      state.cityName = cityName
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_NICK_NAME: (state,nickName) => {
      state.nickName =nickName
    },
    SET_EMAIL:(state,email) =>{
      state.email=email
    }
  },
  actions: {
    issueToken({commit}, val) {
      return new Promise((resolve, reject) => {
        user.issueToken(val).then(data => {
          console.log(data)
          if(data.success){
            commit('SET_TOKEN', data.data)
            setToken(data.data)
            resolve()
          }else{
            reject(data.msg)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    getUserInfo({commit, state}){
      let that = this
      return new Promise((resolve, reject) => {
        user.getUserInfo().then(data => {
          if (data.success) {
            console.log(data)
            commit('SET_IP', data.data.ip)
            commit('SET_CITY_NAME', data.data.cityName)
            commit('SET_ID', data.data.id)
            commit('SET_NICK_NAME',data.data.nickName)
            commit('SET_EMAIL',data.data.email)
            resolve(data)
          } else {
            commit('SET_IP', '')
            commit('SET_CITY_NAME', '')
            commit('SET_ID', '')
            commit('SET_NICK_NAME','')
            commit('SET_EMAIL','')
            // removeToken()
            resolve(data)
          }

        }).catch(error => {
          commit('SET_IP', '')
          commit('SET_CITY_NAME', '')
          commit('SET_ID', '')
          commit('SET_NICK_NAME','')
          commit('SET_EMAIL','')
          // removeToken()
          reject(error)
        })
      })
    },
    // 退出
    logout({commit, state}) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(data => {
          if(data.success){

            commit('SET_TOKEN', '')
            commit('SET_IP', '')
            commit('SET_CITY_NAME', '')
            commit('SET_ID', '')
            removeToken()
            resolve()
          }

        }).catch(error => {
          reject(error)
        })
      })
    },
    // 前端 登出
    fedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_CITY_NAME', '')
        commit('SET_IP', '')
        commit('SET_ID', '')
        removeToken()
        resolve()
      }).catch(error => {
        reject(error)
      })
    },
    register({commit}, user) {
      return new Promise((resolve, reject) => {
        register(user.account, user.nickname, user.password).then((data) => {
          if(data.success){
            commit('SET_TOKEN', data.data)
            setToken(data.data)
            resolve()
          }else{
            reject(data.msg)
          }
        }).catch((error) => {
          reject(error)
        })
      })
    }
  }
})
