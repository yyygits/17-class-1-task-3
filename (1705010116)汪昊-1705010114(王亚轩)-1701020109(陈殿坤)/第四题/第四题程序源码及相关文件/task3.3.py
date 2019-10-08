from tkinter import *
from tkinter.filedialog import askdirectory

import tkinter.messagebox
import tkinter.filedialog
import xlrd
import xlwt
import datetime

def selectPath():
    path_ = tkinter.filedialog.askopenfilename()
    path_ = path_.replace("/", "\\\\")
    path.set(path_)

def rw_excel():
    wb = xlrd.open_workbook(filename=path.get())#打开文件
    print(wb.sheet_names())#获取所有表格名字
    sheet1 = wb.sheet_by_index(0)
    row0 = sheet1.row_values(0)#获取行内容
    col0 = sheet1.col_values(0)#获取列内容
    col1 = sheet1.col_values(1)#获取列内容
    col2 = sheet1.col_values(2)#获取列内容
    col3 = sheet1.col_values(3)#获取列内容
    col4 = sheet1.col_values(4)#获取列内容

    f = xlwt.Workbook()
    sheet2 = f.add_sheet('工资表',cell_overwrite_ok=True)
    for i in range(0, len(row0)):
        sheet2.write(0, i, row0[i], set_style('Times New Roman', 220, True))
    sheet2.write(0, 5, "新工资", set_style('Times New Roman', 220, True))
    for i in range(1, len(col0)):
        sheet2.write(i, 0, col0[i])
    for i in range(1, len(col1)):
        sheet2.write(i, 1, col1[i])
    for i in range(1, len(col2)):
        sheet2.write(i, 2, col2[i])
    for i in range(1, len(col3)):
        sheet2.write(i, 3, col3[i])
    for i in range(1, len(col4)):
        sheet2.write(i, 4, col4[i])
    for i in range(1, len(col4)):
        sheet2.write(i, 5, trans_salary(col2[i],col3[i],col4[i]))
    f.save(path.get())

#设置表格样式
def set_style(name,height,bold=False):
    style = xlwt.XFStyle()
    font = xlwt.Font()
    font.name = name
    font.bold = bold
    font.color_index = 4
    font.height = height
    style.font = font
    return style

def trans_salary(salary, support, enter_year):
    if salary>=26000:
        return salary
    else:
        salary += 100*support + 50*(int(theyear.get())-enter_year)
        return salary if salary <= 26000 else 26000

def transfer():
	try:
		rw_excel()
	except:
		tkinter.messagebox.showerror("错误","请检查原文件格式是否正确\n或请关闭原文件")
	else:
		tkinter.messagebox.showinfo("成功","请检查原文件")

win = Tk()
path = StringVar()
theyear = StringVar()
theyear.set(str(datetime.datetime.now().year))
Label(win,text = "目标路径:").grid(row = 0, column = 0)
Entry(win, textvariable = path).grid(row = 0, column = 1)
Button(win, text = "路径选择", command = selectPath).grid(row = 0, column = 2)
Label(win,text = "截至年:").grid(row = 1, column = 0)
Entry(win, textvariable = theyear).grid(row = 1, column = 1)
Button(win, text = "开始转化", command = transfer).grid(row = 1, column = 2)
screen_height = win.winfo_screenheight()
screen_width = win.winfo_screenwidth()
win.geometry("+%d+%d"%(screen_width/2-100,screen_height/2-100))
win.mainloop()