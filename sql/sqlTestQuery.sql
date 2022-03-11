/****************************************************/
/*                  IMPIEGATO TEST                  */
/****************************************************/

/*  check order id for impiegato */
select o.id
    from ordine o 
        where o.impiegato_id = 4;

/*  check n# order for impiegato */
select i.id, count(o.id) 
    from ordine o, impiegato i 
        where o.impiegato_id=i.id 
            group by i.id;

/*  check total sold for impiegato*/
select i.id, sum(a.prezzo*od.quantita) 
    from ordine o, impiegato i, articolo a, ordine_dettaglio od
        where o.impiegato_id=i.id &&
              od.ordine_id = o.id &&
              a.id = od.articolo_id
                group by i.id;

