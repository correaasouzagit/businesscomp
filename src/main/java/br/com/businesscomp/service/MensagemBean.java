package br.com.businesscomp.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MensagemBean {

public String getHorario() {
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    return "Atualizado em " + sdf.format(new Date());
  }
}
